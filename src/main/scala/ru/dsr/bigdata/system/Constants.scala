package ru.dsr.bigdata.system

object Constants {
  private val DATA_PATH: String = "src/main/resources/sources/"

  val FILE_POPULATION_FM: String = "unsd-citypopulation-year-fm.csv"
  val FILE_POPULATION_BOTH: String = "unsd-citypopulation-year-both.csv"
  val PATH_POPULATION_FM: String = DATA_PATH + FILE_POPULATION_FM
  val PATH_POPULATION_BOTH: String = DATA_PATH + FILE_POPULATION_BOTH

  val MALE = "Male"
  val FEMALE = "Female"

}
