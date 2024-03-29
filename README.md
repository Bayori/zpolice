### Описание
Государственный рп плагин специально для залупки
Из функционала:
- Государственный чат
- Информация об оставшемся времени до конца джейла
- Новая система вызовов 911
- Дубинка (Замена /freeze, дополнение к наручникам)

#### Пермишены

- `zpolice.cdbypass` - Убирает кд на `/911` у игрока [Police] 
- `zpolice.911get` - Возможность получать и принимать вызовы [Police] 
- `zpolice.jailtimeother` - Возможность просматривать jailtime других игроков [Police] 
- `zpolice.goschat` - Возможность писать и принимать сообщения из гос. чата [Необходимо поставить всем лицам, работающим в государстве (Президент, вице-президент, полиция и т.п.)]
- `zpolice.baton` - Возможность использовать `/baton list || unbaton`, а так же саму дубинку в виде предмета [Police]
- `zpolice.forcebaton` - Возможность использовать `/baton` и`/baton <nick>` для укладывания игрока на землю **командой** [Ставить на усмотрение]
- `zpolice.cmdplayer` - Возможность использовать `/cmd` для просмотра списка доступных команд [Все игроки]
- `zpolice.cmdpolice` - Возможность использовать `/cmd` для просмотра списка команд, доступных полицейским [Police]

** [Police] - Необходимо ставить в группу полиции (каждому полицейскому)*
### Подробнее

------------


##### /911
**Использование [Обычный игрок]:** `/911` или `/911 <Комментарий>`. Всем игрокам с пермишеном `zpolice.911get` отсылается уведомление в чат. В самом уведомлении обозначены Ник, координаты и мир игрока, а так же комментарий в том случае, если игрок отправил команду с ним.

**Использование [Полиция]:** При получении уведомления о вызове, в чате будет так же предложено принять вызов кликом на текст, либо можно написать `/911 accept`. При принятии вызова всем полицейским вышлется уведомление о том, кто принял вызов, а вызывающему игроку будет прислано уведомление о том, кто принял его вызов. **Выполняется принятие только последнего вызова** (Работа схожа с `/tpa` в Essentials)
> Система принятия вызовов сильно облегчит работу полицейских. Неоднократно были случаи того, что на вызов слетаются сразу несколько ментов, по итогу остается только один, а другие уходят, ибо требовался изначально всего один, а писать в лс каждому полицейскому (от лица полицейского) очень долго и глупо. Отныне, менты будут знать, кто пойдет на вызов, а вызывающий будет точно знать, что к нему уже летят на помощь.
> Сообщение о вызове теперь выглядит понятно и прилично. Вместо ID мира теперь отображается его название на русском, координаты отличаются по цвету и воспринимать информацию можно намного быстрее
> *Нынешние полицейские оценили это нововедение и хотели бы его видеть на сервере

![Screenshot_10](https://github.com/Bayori/zpolice/assets/32713344/a8c2588a-7697-4b84-9837-f3ef3c15bfb7)
![Screenshot_11](https://github.com/Bayori/zpolice/assets/32713344/86d88227-4568-4d10-b8c6-173da2cc164e)

------------
##### /goschat или /gct
**Использование [Государственный орган]:** `/gct` или `/gct <Сообщение>`. Работает аналогично клановому чату, но чат только для игроков с пермишенном `zpolice.goschat`. Простая отправка `/gct` - включает/выключает госчат. Если он включен, то любое сообщение, отправленное в чат, будет перенаправляться в госчат.
Если сервер выключается, то у игроков с включенным госчатом - он выключается.
> Здорово улучшит коммуникацию между государственными органами, а в частности между полицией
> *Нынешние полицейские оценили это нововедение и хотели бы его видеть на сервере

![Screenshot_15](https://github.com/Bayori/zpolice/assets/32713344/537d1c63-3f31-4406-89ba-c5e3fe131e40)

------------
##### /jailtime
Необходимо: Essentials на сервере

**Использование [Игрок]:** `/jailtime` - Просмотр времени до конца джейла *(Даже если джейлы в режиме online, всё будет работать корректно, независимо от этого)*

**Использование [Полиция]:** `/jailtime <nick>` - Такой же просмотр времени, но у другого игрока, а так же возможность узнать название джейла. Нужен пермишен `zpolice.jailtimeother`.
> 	Теперь полицейским не будут надоедать вечными вопросами: "А скока мне осталось сидеть??", после чего мент брал и через `/co lookup` смотрел время сессий игрока и играл в математику
 	Иногда, полицейским приходилось вручную искать тот джейл, в котором сидит игрок, сейчас же инфа будет известна сразу же

![Screenshot_14](https://github.com/Bayori/zpolice/assets/32713344/9af4ede0-df07-4006-a823-b692ba357ff3)
![Screenshot_13](https://github.com/Bayori/zpolice/assets/32713344/3490f9b7-caec-4001-a9a4-5f426afb5266)

------------
##### /baton; Дубинка
Необходимо: GSit на сервере

**Использование [Полиция]:** Взять обычную палку, переименовать её в "Дубинка", ударить игрока. Игрок, ударенный дубинкой, будет ложиться на пол, не сможет двигаться, не сможет писать команды занесенные в кфг-файл, не сможет использовать эндер-перл, хорус и молоко, не сможет ломать и ставить блоки. **Это рп-моменто как в ирл, человка ударили дубинкой и повалили на пол**. А так же отличное дополнение к наручникам, которые уже есть на Залупе.
Если игрок умирает/ливает с сервака, он автоматически удаляется из списка поваленных игроков. Тоже самое происходит при выключении сервера (плагина).
`/baton list` - Просмотр поваленных игроков.
`/baton unbaton <nick>` - Поставить игрока на ноги.
Нужен пермишен `zpolice.baton`

**Использование [Админ]:** `/baton <nick>` - Повалить игрока командой. Нужен пермишен `zpolice.forcebaton` и `zpolice.baton`

`batonBlackListCommands.yml` - В директории плагина - файл со списком запрещенных команд при лежании. (Есть команда в файле - игрок не сможет её юзать если его повалили)
> Отличное дополнение к рп-действиям, таких как наручники

https://github.com/Bayori/zpolice/assets/32713344/9a8e39b6-255a-4358-8a2f-bc7411d0c01b

------------
##### /cmd
Аналог команды /help. Выводит список доступных команд игроку.
Необходим пермишен `zpolice.cmdplayer`
Если есть пермишен `zpolice.cmdpolice`, то дополнительно будет выведен список команд, доступных полицейским.

Список полностью редактируется через файлы `cmdHelpPlayers.yml` и `cmdHelpPolice.yml` в директории плагина

![Screenshot_10](https://github.com/Bayori/zpolice/assets/32713344/f0fe0210-fb02-440c-9e0f-acb23eaa2f4e)
![Screenshot_11](https://github.com/Bayori/zpolice/assets/32713344/a1ba7972-cefe-4a70-a8da-2f80f40e309d)

------------
Данный плагин (zpolice) полностью заменит нынешний (simplepolice)
