# JNet

This is an API to handle with the network servers and the whole internet.   
 It is an high level API to do many tasks. The main target is to help building Server-Client and webbased APIs and applications.   


## Table of content

1. Branching
2. Features
	
    * Text Based
    * Package Based

## Branching

* **Master** : Only completely Tested features. This branch has only stable versions so it is maybe a bit old but the advantage is that you can trust it as the features which are in are always working. All Test cases and upcomming ones are covered and (will) work
* **Alpha** : The alpha branch is the Accurate Testing branch. The versions in here seem to be stable but still need accurate testing. You can use this as stable version but it has maybe a few hidden bugs and issues. All Test cases are covered and work. (semi-stable)
* **Text Based** : This brach regular get updates and contains all new features of the text based servers. It also contains alpha Json Features. Mean the Json features which at least work in the test cases so its Semi-stable. It is very unstable may contains non working test cases
* **Binary Based** : This brach regular get updates and contains all new binary based features. It is very unstable may contains non working test cases
* **Json** : This branch regular get updates and contains all new features of the json based servers. It is very unstable may contains non working test cases
## Text Based

### Specs
*   State                 : In Development
*   Latest Stable Version : -
*   Current Version       : 1.0.0
*   package               : com.nbrugger.jnet.text

> This module uses my [Media API][mapi] as JSON Parser and Serializer and the stream System.

### Description

Text based means that the server and client communicate with Strings -> over text.  
So if you have an Server the client should send strings as command.  
For example:
Client sends the text "login name='nils' pwd='das'" and the server respondes "loginstate='102'" where 102 means "user not found" for example   
The advantage of this method is that it is cross platform and cross language.  
So if i make a text server you can call it by any language from any system which can use networks   

### Examples







[mapi]: https://github.com/nbrugger-tgm/Niton-Media-Framework "Media API" 