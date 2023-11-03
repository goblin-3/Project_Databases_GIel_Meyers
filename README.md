# Project_Databases_GIel_Meyers:

Het doel van deze database is een overzicht bij te houden van zowel de locaties als de status van de verschillende games en bijhorende Miscellaneous (Misc.).
Zoals gevraagd stelt het DDL-schema enkel de hoofdtabellen voor, de tussentabellen zijn hier niet op weergegeven. Achter elke variabele heb ik de SQLite types weergegeven.

In de hoofdtabel Game houden we al de gegevens bij over de games zoals: de naam van de Game, Releaseyear, het Genre, de Language, de Publisher, de prijs van de game en of een game al dan niet digitaal is.  In de tabel Console houden we ook de PC-spellen bij.  De prijs wordt per spel ingegeven; deze waarde kan niet nul en niet negatief zijn. De waarde is niet gelinkt aan een extra tabel. De andere hoofdtabel houdt de niet-spel gegevens bij onder de naam Misc. In deze tabel Misc houden we dezelfde gegevens bij als in de tabel Game maar in plaats van de tabel Console kiezen we hier voor de tabel Type waarin we posters, T-shirts, handleidingen, etc. als soorten kunnen opslaan.

De tabellen Genres, Console/Type, Language, Releaseyear en Publisher zijn aanpasbare lijsten met unieke gegevens die via een tussentabel gelinkt zijn aan de hoofdtabellen Game en Misc.  Deze aparte tabellen en tussentabellen laten toe om gemakkelijker op deze inhoud te zoeken of te filteren.

Het bijhouden van de gegevens in de tabellen Genre, Console/Type, Releaseyear, Language, Publisher en Console zorgen ervoor dat elke waarde in deze tabellen slechts éénmaal ingevoerd zal worden. Enkel de Admin en Employee hebben toegang tot de input van deze lijsten. Dit vermindert de foutenlast en voorkomt het bijhouden van dubbele gegevens.
De naam van het spel, bijvoorbeeld ‘New Super Mario Brothers’ kan wel meerdere keren voorkomen in de database; als deze gereleaset wordt voor meerdere types Consoles. Het is ook mogelijk dat er twee of meerdere dezelfde exemplaren aanwezig zijn in de collectie van onze musea. We kunnen deze gemakkelijk van elkaar onderscheiden omdat elke spel zijn eigen unieke nummer heeft. 
Tevens vergemakkelijkt het de zoekopdracht. Het zorgt ervoor dat de gebruiker deze gegevens kan selecteren uit een keuzelijst. Het is de bedoeling dat deze gebruiker met een eenvoudige zoekopdracht de gezochte game of een Misc kan vinden zonder dat de gebruiker de gegevens kan manipuleren.

In de tabellen Musea en Warehouse houden we de locatie van elke game en Misc bij.
Concreet betekent dit dat we die locatie bijhouden in een tussentabel tussen Musea-Game en Warehouse-Game alsook tussen User en Game. 

De tabel User is opgesplitst in drie verschillende tabellen: Admin, Customers en Employee. Tussen deze tabel User en de drie vermelde tabellen bevinden zich tussentabellen om de verschillende types gebruikers gescheiden te houden. Zo kan een Customer zichzelf geen admin privileges geven door een onvoorziene fout in het programma. De Customer kan enkel raadplegen via een zoekfunctie. Het door de Customer gekozen spel of Misc houden we bij in een tussentabel. Ze houden alleen de noodzakelijke gegevens bij zoals de naam en voornaam van de gebruikers, hun emailadres en het gekozen paswoord.

De tabel Money staat zonder verbindingslijnen in het schema. Hier houden we bij hoeveel budget er ter beschikking is voor al onze musea. Dit budget kan niet negatief zijn. Het doel is om de opbrengst van de spellen op te tellen bij het bestaande totaalbudget.
