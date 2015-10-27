Die Konfiguration ist aktuell noch in der Klasse de.sourcepark.hubbabubba.Config enthalten. 
Das darin referenzierte authorizationFile (csv) hat folgendes Format: <Karten-ID>[;<Nickname>][;Begrüßung]

Autorisierungsanfragen werden an den ControlUnitService (de.sourcepark.hubbabubba.services) geschickt: 
POST /control/authorize/:id

Die Listenerregistrierung erfolgt über den ListenerService (de.sourcepark.hubbabubba.services.listenerservice).
Register: POST /listener/register
Unregister: POST /listener/unregister
Erwartet wird JSON, siehe Domain Object de.sourcepark.hubbabubba.domain.ListenerRegistration. Identifizierung des Listeners anhand des Namens.
Leider wird damit noch nichts gemacht...

