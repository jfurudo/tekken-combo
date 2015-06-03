// package test001

// import scalikejdbc._
// import skinny.dbmigration.DBSeeds

// trait CreateTables extends DBSeeds { self: Connection =>

//   override val dbSeedsAutoSession = NamedAutoSession('test001)

//   addSeedSQL(
//     sql"""
// create table user (
//   id bigint auto_increment not null primary key,
//   twitter_id bigint not null,
//   name varchar(255)
// """)

//   runIfFailed(sql"select count(1) from user")
// }
