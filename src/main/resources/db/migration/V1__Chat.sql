CREATE TABLE `Chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chat` varchar(512) , user int(11) not null,
  created datetime not null,
  PRIMARY KEY (`id`)
) ;

create table User(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  name varchar(8) not null
);