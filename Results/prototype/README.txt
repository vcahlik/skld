# Inštalácia databáze

Základným predpokladom úspešnej prevádzky systému je inštalácia databázového serveru PostgreSQL na server. Binárne inštalačné balíky pre rôzne podporované systémy sa dajú stiahnuť zo stránky https://www.postgresql.org/download/ alebo z balíčkových manažérov rôznych Linuxových distribúcií.

Po inštalácií databázového serveru je na ňom nutné vytvoriť databázu a jej schému. Ideálne, pre jednoduchosť konfigurácie, je, ak sa táto databáza volá skld. Jej schéma a malé množstvo vzorových dát sa nachádza v repozitári v zložke Results/prototype a súbore db.sql. Do databázy sa dá táto schéma nahrať pomocou príkazu psql použitého ako psql -d nazov_databazy < db.sql.

# Inštalácia a konfigurácia serveru

Server je zabalený do balíčka api.jar. Jeho konfigurácia sa nachádza v YAML súbore config.yml.  Pre jednoduché testovanie je v ňom najrelevantnejšia podsekcia database. V nej je nutné nastaviť prihlasovacie  údaje na databázový server a názov databáze. Ďalšia dôležitá sekcia je server, kde sa dá nastaviť port a adresa, na ktorých bude HTTP server API počúvať.

Kritické je, aby užívateľ nastavil v konfigurácií možnosť jwtSecret na náhodný reťazec vlastného výberu. Ten slúži ako kľúč na podpisovanie tokenov, pomocov ktorých sa užívatela prihlasujú. Jeho prednastavená hodnota je zdieľaná medzi všetkými užívateľmi, takže ktorýkoľvek iný zákazník aplikácie by mohol falšovať užívateľské prihlasovacie tokeny!

Napokon sa server dá spustiť pomocou príkazu `java -jar api.jar server config.yml`. Pre beh vyžaduje JRE 1.8.

# Konfigurácia a spustenie klienta

Klient se jednoduše spustí příkazem java -jar gui.jar. Pro spuštění vyžaduje JRE 1.8 s podporou JavaFX 2 (Oracle JRE by to měl zvládat implicitně, OpenJRE technologii JavaFX rovněž podporuje, avšak obvykle se musí doinstalovat, na většině linuxových distribucí balíčkem openjfx).

Pro nastavení serveru, se kterým klient bude komunikovat je možné použít mechanismus Java Properties, konkrétně property serverUrl. Například ho teda je možné spustit příkazem java -DserverUrl=http://localhost:8080 -jar gui.jar

Při testování a prvotním nasazení aplikace je možné využít předvytvořený administrační účet, který má PIN 1234. Doporučujeme tomuhle účtu změnit PIN nebo ho odstranit hned, jak to je možné.