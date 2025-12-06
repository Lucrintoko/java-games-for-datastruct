

jar cfm GridBlast.jar manifest.txt -C out .


javac -d out src\main\java\com\gridblast\*.java src\main\java\com\gridblast\model\*.java src\main\java\com\gridblast\model\data\*.java src\main\java\com\gridblast\view\*.java src\main\java\com\gridblast\controller\*.java src\main\java\com\gridblast\util\*.java