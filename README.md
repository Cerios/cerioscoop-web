# cerioscoop-web
We're Cerios! A young company with about 60 employees that helps organisations to successfully realize complex IT project. Cerios started educating Java software developers and came up with the idea for them to create a cinema web application. It is intended that the web application will be gradually expanded further over time. In the near future there will also be testers participating in this project to optimally stimulate the learning curve and collaboration.

## Installation

* Download [Eclipse](https://eclipse.org/downloads/). If you want to use WebSphere Liberty Profile, then make sure you use Eclipse version '[Mars](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/mars/R2/eclipse-inst-win64.exe)'
* Install [IBM WebSphere Liberty Profile Server](https://developer.ibm.com/wasdev/getstarted/) and add it to Eclipse (click [here](https://developer.ibm.com/wasdev/docs/developing-applications-wdt-liberty-profile/) for more information)
* Make sure you have installed the [Eclipse tools for WebSphere Liberty](https://developer.ibm.com/wasdev/downloads/)! This will also include the Maven plugin etc.
* Select _Import_ > _Maven_ > _Checkout Maven Projects from SCM_ and select git with [this link](https://github.com/RonSanders/cerioscoop-web.git). (If necessary install the git m2e connector from the link in the lower right corner)
* Select _Properties_ > _Project Facets_ and check _Dynamic Web Module_
* Add a DataSource to the WebSphere Liberty Profile Server as described [here](http://www.ibm.com/support/knowledgecenter/was_beta_liberty/com.ibm.websphere.wlp.nd.multiplatform.doc/ae/twlp_dep_configuring_ds.html)
* Install and setup [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
* Install and setup [HeidiSQL](http://www.heidisql.com/download.php), useful and reliable tool designed for web developers using the popular MySQL server
* Visualize and manage your repositories with [SourceTree](https://www.atlassian.com/software/sourcetree) or simply use github's own visualisation tool [Github Desktop](https://desktop.github.com)

## Contributors
* Ron Sanders
* Rutger van Velzen
* Marcel Groothuis
* Friso Schutte
* Remco Hoetner
* Gijsbert Peijs
* Rob Bosman
* Bob van Zeist
