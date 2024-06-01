
## Liste de toutes les dépendances :
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.0")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.gms:play-services-location:21.2.0")
    implementation ("com.fasterxml.jackson.core:jackson-core:2.12.3")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.12.3")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

## Implementation des Patterns:

   ### Le pattern Factory
   Le pattern Factory implémenté est Factory Method: (package: models)
   -Fabrique générique:SignalementFacotry
   -Fabrique de signalement:NormalSignalementFactory,UrgentSignalementFactory

   ### Le pattern MVC
   Le pattern MVC est implementé dans le package agent_equipements_view:
   L'activité est responsable de gérer les équipments de l'agent à transporter
   pour résoudre un incident

   ### Parcelable
   Les differents types de signalements ainsi que que le user implemente Parcelable:
   Le User est une varaible unique
   La liste des signalements dans notre choix d'implementation (futur) n'est pas identique dans tout le programme
   et diffère selon l'onglet de l'utilisation ( signalements généraux ou mes signalements => differents requêtes)

   ### Le pattern Singleton
   Le pattern Sigleton a été implmenté dans le WebService (package: web_service) : qui sera unique et utilisé dans toute l'application
   (Mais aussi pour réaliser notre démo avant la réalisation de notre produit final
   nous avons utilisé une classe de Mocks singleton pour synchroniser les différents informations dans notre programme)

   ### Le pattern Observer/Observable
   Le pattern Observer/Observable a été implementé dans un filtre de niveau minimal dans l'affichage
   de la liste des signalements pour l'agent qui filtrera les incident selon le niveau d'importance
   (package: agent_signalement_view)
   Observable : NiveauMinimalSensor
   Observer:NiveauFilter
