# Masche-Tool

#### Ein akteursorientiertes Lehrveranstaltungsevaluationssystem für Hochschulen

Das Masche-Tool ist ein freies, akteursorientiertes Lehrveranstaltungsevaluationssystem für Hochschulen und wurde entwickelt um Vorlesungen, Seminare oder auch Tutorien zu evaluieren.

Das Masche-Tool ist im Rahmen einer Masterarbeit der Wirtschaftsinformatik an der Universität Oldenburg entworfen und entwickelt worden. In diesem Repository finden sich alle benötigten Informationen, um das System auf einem eigenen Server aufzusetzen.

## Systemanforderungen
- Servlet-Container (z.B. Tomcat > 6.0)
- MySQL-Datenbank

## Installationsanleitung
- Zuerst wird eine neue Datenbank mit einem entsprechen Benutzer angelegt. Die Zugangsdaten werden dann in src/model/Datenbank.java eingetragen und das SQL-Skipt CREATETABLES.sql auf der neuen Datenbank ausgeführt.
- Einen JDBC-Treiber (Version 5.1.38) für MySQL liefert das Masche-Tool bereits mit.
- Das System wird in einen WAR-Container compiliert, der dem tomcat-Server zur Verfügung gestellt wird, wonach dieser zu starten ist. Dieses Vorgehen kann je nach verwendeten Betriebssystem und Entwicklungsumgebung variieren.
- Um die Sicherheit der Evaluations-TeilnehmerInnen zu wahren, sollte ein HTTPS-Reverse-Proxy verwendet werden, alternativ kann auch der Servlet-Conatiner für HTTPS konfiguriert werden.
- Die System-URL, unter der das Masche-Tool verfügbar sein soll, wird in der Tabelle hostInfos mit der ID=0 eingetragen (bspw. https://example.com). Das System erwartet unter /masche veröffentlicht zu werden.
- Zusätzlich muss ein initialer Benutzer in die Tabelle USER eingetragen werden.
- Nun sollte das System einsatzbereit sein. Viel Spaß beim Evaluieren.