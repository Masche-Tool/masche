CREATE TABLE `SESSIONS` (
  `User` varchar(32) NOT NULL,
  `Session` varchar(32) NOT NULL,
  `timeCreated` datetime NOT NULL,
  PRIMARY KEY (`Session`),
  UNIQUE KEY `Session` (`Session`),
  KEY `UserID` (`User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `USER` (
  `User` varchar(32) NOT NULL,
  `pw` varchar(45) DEFAULT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `titel` varchar(40) NOT NULL DEFAULT ' ',
  `Name` text NOT NULL,
  `EMail` varchar(120) NOT NULL DEFAULT '-/-',
  `lastLogin` datetime NOT NULL
        DEFAULT '1000-01-01 00:00:00',
  `lastLoginIP` varchar(50) NOT NULL
        DEFAULT '0.0.0.0',
  `lastAction` datetime NOT NULL
        DEFAULT '1000-01-01 00:00:00',
  `lastLoginUserAgent` text,
  PRIMARY KEY (`User`),
  UNIQUE KEY `User` (`User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `evAntwort` (
  `participantSession` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `Antwort` varchar(1024)
        COLLATE utf8_unicode_ci DEFAULT NULL,
  `FrageID` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `Zeit` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8
        COLLATE=utf8_unicode_ci;

CREATE TABLE `participantSESSIONS` (
  `participantSESSIONS` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `DurchfuehrungID` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`participantSESSIONS`),
  UNIQUE KEY `participantSESSIONS`
        (`participantSESSIONS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
      COLLATE=utf8_unicode_ci;

CREATE TABLE `evFrage` (
  `Fragebogenreferenz` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `FrageID` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `Nummer` int(11) NOT NULL,
  `Frage` varchar(512)
        COLLATE utf8_unicode_ci NOT NULL,
  `Art` int(11) NOT NULL,
  PRIMARY KEY (`FrageID`),
  UNIQUE KEY `FragenID` (`FrageID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
      COLLATE=utf8_unicode_ci;

CREATE TABLE `hostInfos` (
  `ID` int(11) NOT NULL,
  `SystemURL` varchar(150)
        COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SystemURL`
        (`SystemURL`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
      COLLATE=utf8_unicode_ci;

CREATE TABLE `evFragebogen` (
  `ID` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `Fragenreferenz` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `Ersteller` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `Besitzer` varchar(32)
        COLLATE utf8_unicode_ci NOT NULL,
  `Bearbeitungsszeit` datetime NOT NULL,
  `DurchfuehrungID` varchar(32)
        COLLATE utf8_unicode_ci DEFAULT NULL,
  `freigeschaltet` tinyint(4)
        NOT NULL DEFAULT '1',
  `Semester` varchar(64)
        COLLATE utf8_unicode_ci NOT NULL,
  `Modulname` varchar(256)
        COLLATE utf8_unicode_ci NOT NULL,
  `Beschreibung` varchar(1024)
        COLLATE utf8_unicode_ci NOT NULL,
  `MagicKey` varchar(32)
        COLLATE utf8_unicode_ci DEFAULT NULL,
  `AbgeleitetVon` varchar(32)
        COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Erstellungszeit`
        (`Bearbeitungsszeit`),
  UNIQUE KEY `ID` (`ID`),
  UNIQUE KEY `DurchfuehrungID` (`DurchfuehrungID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
      COLLATE=utf8_unicode_ci;




        
