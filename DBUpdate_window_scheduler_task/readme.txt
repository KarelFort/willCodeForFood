Adresář AppDBUpdate/run uložte na libovolné místo na disku. 
V souboru ConnectionDetails.csv změnte údaje o připojení k aplikační a zdrojové databázi. 
Tento soubor je vstupním parametrem pro aktualizační program. 
Pro periodické spouštění programu vytvořte úlohu v programu windows scheduler (plánovač úloh). 
Vytvořte novou úlohu. V sekci akce vyberte skript run.bat, který spouští jar archiv s programem 
a nastavte cestu k tomuto skriptu v poli “Start in (optional)”. Další nastavení zvolte podle vašeho uvážení.
