# cerioscoop-web
We're Cerios! A young company with about 60 employees that helps organisations to successfully realize complex IT project. Cerios started educating Java software developers and came up with the idea for them to create a cinema web application. It is intended that the web application will be gradually expanded further over time. In the near future there will also be testers participating in this project to optimally stimulate the learning curve and collaboration.

## Installation
* Download [Eclipse](https://eclipse.org/downloads/). If you want to use WebSphere Liberty Profile, then make sure you use Eclipse version '[Mars](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/mars/R2/eclipse-inst-win64.exe)'
* Visualize and manage your repositories with [SourceTree](https://www.atlassian.com/software/sourcetree)
* 'Import' > 'Projects from git' > 'Clone url' (using [this link](https://github.com/RonSanders/cerioscoop-web.git)) > 'Import as general project' > 'Finish'
* 'Configure' > 'Convert to Maven project'
* 'Properties' > 'Project Facets', check option '_Dynamic Web Module_', __then click__ '_Further configuration available..._' and set the _Content directory_ to `src/main/webapp`
* Install and setup [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
* Install and setup [HeidiSQL](http://www.heidisql.com/download.php), useful and reliable tool designed for web developers using the popular MySQL server
* Install [IBM WebSphere Liberty Profile Server](https://developer.ibm.com/wasdev/getstarted/) and add it to Eclipse (click [here](https://developer.ibm.com/wasdev/docs/developing-applications-wdt-liberty-profile/) for more information)
* Add a DataSource to the WebSphere Liberty Profile Server as described [here](http://www.ibm.com/support/knowledgecenter/was_beta_liberty/com.ibm.websphere.wlp.nd.multiplatform.doc/ae/twlp_dep_configuring_ds.html)
* Get the [jenkins.war](https://wiki.jenkins-ci.org/display/JENKINS/Meet+Jenkins) file for continuous integration

## Contributors
* Ron Sanders
* Rutger van Velzen
* Marcel Groothuis
* Friso Schutte
* Remco Hoetner
* Gijsbert Peijs
* Rob Bosman
* Bob van Zeist
