# ClinicalSystemISABack

To start a server side of application, it requires to have Java 1.8. 
POSTGRESql database is required. You can install and start it through Docker Desktop, or simply download and install POSTGRESql and start it through Services.
Next thing you should do is to clone project from this repostory. 
Import every changes Maven requires to do.
In application properties file(in resources) manually write username and password for your postgre server.
Set data definition language to create(or create-drop) so tables will be created when you start your application.
For email properties, still in application properties, for username and password set email account through you will send emails for authentications.
Last thing to do, is to start project through Play icon(green icon in top-right corner).
