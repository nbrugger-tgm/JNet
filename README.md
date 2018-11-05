# JNet

This is an API to handle with the network servers and the whole internet.   
 It is an high level API to do many tasks. The main target is to help building Server-Client and webbased APIs and applications.   


## Table of content


1. Features
	
*   Text Based
*   Package Based


## Text Based

### Specs
*   State                 : Currently done
*   Latest Stable Version : 1.2.0
*   Current Version       : 1.2.0
*   package               : com.nbrugger.jnet.text

> This module uses my [Media API][mapi] as JSON Parser and Serializer and the stream System.

### Description

Text based means that the server and client communicate with Strings -> over text. Examples for this is HTTP or SMTP.
Which means you can also build web or mail servers using this tool (Only as web interface)
So if you have an Server the client should send strings as command.  
For example:
Client sends the text "login name='nils' pwd='das'" and the server respondes "loginstate='102'" where 102 means "user not found" for example   
The advantage of this method is that it is cross platform and cross language and easy testable with clients like telnet.  
So if i make a text server you can call it by any language from any system which is able to use networks   

### Examples
Can be found in the package
> com.nbrugger.jnet.examples

and
> com.nbrugger.jnet.test

We recommend to only use the components in the 
> com.nbrugger.jnet.text.buffering

package.

[mapi]: https://github.com/nbrugger-tgm/Niton-Media-Framework "Media API" 