@echo off

set waitarg=
set ideargs=

:next
set "passwait="
if "%~1"=="--wait" set passwait=1
if "%~1"=="-w" set passwait=1
if defined passwait (set waitarg=/wait)
if not "%~1"=="" (
  if defined passwait (set "ideargs=%ideargs%--wait ") else (set "ideargs=%ideargs%%1 ")
  shift
  goto next
)

start "" %waitarg% ":idepath" %ideargs%