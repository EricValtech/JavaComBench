REM @echo off
REM Change protoc home...
set PROTOC_HOME=D:\00-User\Eric\00-Projets\12-Vidal\Tools\protoc
%PROTOC_HOME%\protoc -I=.\ --java_out=..\..\java\ .\simplemodel.prot
%PROTOC_HOME%\protoc -I=.\ --java_out=..\..\java\ .\fulltypesmodel.prot
%PROTOC_HOME%\protoc -I=.\ --java_out=..\..\java\ .\packcontainer.prot