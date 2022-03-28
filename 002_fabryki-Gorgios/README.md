# Wzorce: Fabryki

| Termin oddania | Punkty     |
|----------------|:-----------|
| 11.04.2021 23:00|  10       |

--- 
Przekroczenie terminu o **n** zajęć wiąże się z karą:
- punkty uzyskania za realizację zadania są dzielone przez **2<sup>n</sup>**.

--- 
1. Klient potrzebuje obiekty należące do kilku (3-4) rodzajów produktów.
    * Każdy rodzaj posiada kilka (5-6) konkretnych realizacji.
    * Wymyślić i wytworzyć kod opisujący produkty i ich rodzaje.

1. Dla rozdzielenia procesu wytwarzania obiektów od klas klienta z poprzedniego punktu, 
	zastosować fabrykę zaimplementowaną jako singleton. 
	Zaprezentować pozytywne i negatywne skutki zastosowania:
    * fabryki prostej
    * fabryki z metodą wytwórczą
    * fabryki abstrakcyjnej

1. Porównać złożoność i efektywność działania fabryk z poprzedniego punktu
   z fabryką używającą rejestracji klas z wykorzystaniem refleksji oraz bez wykorzystania refleksji

Do zaproponowanych rozwiązań załączyć odpowiednie testy wydajnościowe / funkcjonalne.
Można używać narzędzi do profilowania kodu dedykowanych danej technologii. 
