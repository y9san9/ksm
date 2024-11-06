# Telegram

This module contains typed accessors for `telegram-plugin` module. 
It also exports API from `telegram-api` module.

I decided to separate `plugin` module, because it adds a lot of classes 
to global namespace (which are not required for almost anyone) 
which might result in IDE slow down,
