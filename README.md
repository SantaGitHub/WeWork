## WeWork - возможность закрывать сервер без его выключения ##

WeWork это простой плагин для Bukkit, который позволяет закрывать сервер без его выключения, оставляя возможность заходить только OP и людям/группам с разрешением 'wework.join'.

Плагин будет полезен когда появляется необходимость провести профилактику/улучшение сервера, но на сервере большой онлайн и частые перезагрузки будут мешать игрокам.

## Конфигурация ##

Все настройки плагина хранятся в файле config.yml в папке WeWork.

**enabled:** данная настройка позволяет отключить/включить плагин (true/false)

**debug:** включает/отключает отладчик.

**tag:** содержит первую строку от сообщения показываемого при отключении игрока у которого нет доступа к серверу

**message:** содержит вторую строку от сообщения показываемого при отключении игрока у которого нет доступа к серверу

**joinMessage:** содержит сообщение выводимое при входе игрока на сервер у которого есть право на вход

**closing:** содержит сообщение выводимое отправителю при закрытии сервера

**opening:** содержит сообщение выводимое отправителю при открытии сервера

##Permissions(Разрешения)##

**wework.*** - разрешает все действия связанные с плагином

**wework.usage** - разрешает использовать команду /wework [on/off], которая включает/выключает плагин

**wework.join** - позволяет входить игроку на закрытый сервер т.е когда плагин включен

## Лицензия ##
Использование данного программного обеспечения осуществляется по правилам zlib/libpng License.

Для ознакомления смотрите файл license.txt