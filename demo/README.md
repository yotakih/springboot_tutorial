# Springboot プロジェクトサンプル
## フォルダ構造
├── pom.xml		このプロジェクトで必要なモジュールを記載しておくとmavenがダウンロードしてきてくれる。
└── src
    ├── main																												
    │   ├── java
    │   │   └── com
    │   │       └── example
    │   │           └── demo
    │   │               ├── DemoApplication.java
    │   │               ├── app
    │   │               │   ├── controller
    │   │               │   │   ├── PlayerController.java						画面を返すコントローラ
    │   │               │   │   └── PlayerRestController.java				APIコントローラ
    │   │               │   └── form
    │   │               │       ├── PlayerInputRequestForm.java			APIコントローラのPOSTデータ型
    │   │               │       └── PlayerOutputRequestForm.java		APIコントローラの返却JSONデータ型
    │   │               └── domain
    │   │                   ├── repository
    │   │                   │   └── PlayerRepository.java
    │   │                   └── service
    │   │                       ├── PlayerDto.java
    │   │                       └── PlayerService.java
    │   └── resources
    │       ├── application.properties
    │       ├── com
    │       │   └── example
    │       │       └── demo
    │       │           └── domain
    │       │               └── repository
    │       │                   └── PlayerRepository.xml
    │       ├── static
    │       └── templates
    │           └── players.html
    └── test
        ├── java
        │   └── com
        │       └── example
        │           └── demo
        │               ├── DemoApplicationTests.java
        │               ├── app
        │               │   ├── controller
        │               │   │   ├── PlayerControllerTest.java
        │               │   │   └── PlayerRestControllerTest.java
        │               │   └── form
        │               │       └── PlayerInputRequestFormTest.java
        │               └── domain
        │                   ├── common
        │                   │   └── CSVDataSetLoader.java
        │                   ├── repository
        │                   │   └── PlayerRepositoryTest.java
        │                   └── service
        │                       └── PlayerServiceTest.java
        └── resource
            ├── ValidationMessages.properties
            └── com
                └── example
                    └── demo
                        └── domain
                            └── repository
                                ├── datas
                                │   ├── player.csv
                                │   └── table-ordering.txt
                                └── expectdata
                                    ├── player.csv
                                    └── table-ordering.txt

