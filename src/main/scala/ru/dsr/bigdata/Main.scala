package ru.dsr.bigdata

import org.apache.spark.sql.{Dataset, Row, SparkSession}
import org.apache.spark.{SparkConf, SparkFiles}

object Main extends App{

  private val sparkConf = new SparkConf().setMaster(AppConfig.spark_master)
  implicit val spark: SparkSession = SparkSession.builder()
    .config(sparkConf)
    .config("spark.mongodb.output.uri", AppConfig.output_uri)
    .getOrCreate()

  var fm: Dataset[Row] = _
  var both: Dataset[Row] = _

  print(SparkFiles.getRootDirectory())

  AppConfig.load_from match {
    case "path" =>
      fm = DataLoad.loadFromPath(AppConfig.fm_path)
      both = DataLoad.loadFromPath(AppConfig.both_path)
    case "url" =>
      fm = DataLoad.loadFromUrl(AppConfig.fm_url)
      both = DataLoad.loadFromUrl(AppConfig.both_url)
    case _ =>
      println("Wrong config load_from!")
  }

  AppConfig.save_to match {
    case "mongodb" =>
      Tools.saveToMongoDB(Job.getPopulation(both), "population")
      Tools.saveToMongoDB(Job.getCountMillionCities(both), "countMillionCities")
      Tools.saveToMongoDB(Job.getTop5Cities(both), "top5Cities")
      Tools.saveToMongoDB(Job.getRatioPopulation(fm), "ratioPopulation")
    case "csv" =>
      Tools.saveToCsv(Job.getPopulation(both), "population.csv")
      Tools.saveToCsv(Job.getCountMillionCities(both), "countMillionCities.csv")
      Tools.saveToCsv(Job.getTop5Cities(both), "top5Cities.csv")
      Tools.saveToCsv(Job.getRatioPopulation(fm), "ratioPopulation.csv")
    case _ =>
      println("Wrong config save_to!")
  }

}
