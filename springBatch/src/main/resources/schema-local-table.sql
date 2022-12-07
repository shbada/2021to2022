drop table temp_library;
drop table temp_library_local;
drop table temp_library_type;

CREATE TABLE `temp_library` (
                                `idx` int(11) NOT NULL AUTO_INCREMENT,
                                `library_nm` varchar(200) DEFAULT NULL,
                                `big_local` varchar(200) DEFAULT NULL,
                                `small_local` varchar(200) DEFAULT NULL,
                                `library_type` varchar(200) DEFAULT NULL,
                                PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `temp_library_local` (
                                      `idx` int(11) NOT NULL AUTO_INCREMENT,
                                      `big_local` varchar(200) DEFAULT NULL,
                                      `small_local` varchar(200) DEFAULT NULL,
                                      PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `temp_library_type` (
                                     `idx` int(11) NOT NULL AUTO_INCREMENT,
                                     `library_type` varchar(200) DEFAULT NULL,
                                     PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

commit;