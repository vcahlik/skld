# Pokyny k nastaveniu
Aplikácia je implementovaná ako dve hlavné komponenty - REST API a GUI. 
Obidve musia bežať (nie nutne na tom istom serveri), aby sa dala používať.
REST API naviac vyžaduje bežiacu PostgreSQL databázu.

## Nastavenie servera
Server je zabalený do balíčka api.jar. Jeho konfigurácia sa nachádza v YAML súbore
config.yml. Pre jednoduché testovanie je v ňom relevantná jediná podsekcia a to `database`.
V nej je nutné nastaviť prihlasovacie údaje na databázový server.

API vyžaduje na databázovom serveri určitú tabuľkovú schému, popísanú v návrhovej
dokumentácií systému. Tá sa samozrejme dá vygenerovať z Enterprise Architecta, ale pri
testovaní aplikácie môže byť výrazne pohodlnejšie nahrať si priložený databázový dump
prostredníctvom príkazu `psql -d nazov_databazy < db.sql`

Napokon sa server dá spustiť pomocou príkazu `java -jar api.jar server config.yml`.
Pre beh vyžaduje JRE 1.8.

Do aplikácie sa dá prihlásiť ako administrátor pomocou PIN kódov `1234` a `1984`.