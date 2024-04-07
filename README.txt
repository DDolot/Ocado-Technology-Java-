# Projekt BasketSplitter
#1 metody 

Klasa BasketSplitter zawiera następujące metody:

calculateOccurances - metoda która tworzy mapę przechwoującą ilość występowań poszczególnych sposobów dostawy.  

PriorityDeliveryMethod - metoda ta zwraca posortowaną listę tych dostaw.

loadConfigFile - metoda wczytuje plik konfiguracyjny tj. czyta plik json i zwraca mape 
Map<String,List<String>>

split - głowna metoda odpowiedzialna za logikę rozdzielania produków w koszyku

#2 algorytm

logika mojego algorytmu polega na znalezieniu dla danego zostawu produktów w koszyku najczęsciej pojawiającej się metody dostawy(priotytet) a nastepnie dodanie tej metody do mapy jako klucz oraz dodanie wszystkich produktów które mogą być wysłane w ten sposob jako wartość.Następnie powtarzam czynność obliczenia priorytetu,ale dla koszyka bez produków które dodałem wcześniej.

#3 Przemyślenia 

Mam świadomość niedoskonałości mojego kodu sam algorytm nie jest napisny wydajnie a tesów jest mało,wynikało to bardziej z braku czasu,jednak mam nadzieję że rozważą państwo moją kandydaturę na to stanowsko :) 