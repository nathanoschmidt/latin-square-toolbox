#!/bin/sh
toolbox_version="1.10"
MYSHELL=`which "$0" 2>/dev/null`
[ $? -gt 0 -a -f "$0" ] && MYSHELL="./$0"
java=java
java_args="-cp target/latin-square-toolbox-$toolbox_version-SNAPSHOT.jar latinsquare.LatinSquareToolTransversalCounter"
if test -n "$JAVA_HOME"; then
    java="$JAVA_HOME/bin/java"
fi
#echo "args: $@"
#echo "myshell: $MYSHELL"
exec "$java" $java_args "$@"
#exec "$java" $java_args -jar $MYSHELL "$@"
exit 1
