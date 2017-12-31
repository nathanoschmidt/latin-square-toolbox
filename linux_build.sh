#!/bin/sh

base_dir="."
script_dir="$base_dir/scripts"
deploy_dir="$base_dir/target"
stub_generator="$script_dir/linux_stub_generator_tool.sh"
stub_transversal_counter="$script_dir/linux_stub_transversal_counter_tool.sh"
stub_property_checker="$script_dir/linux_stub_property_checker_tool.sh"
maven_cmd="mvn"
toolbox_version="1.10"
toolbox_deployed_jar="$deploy_dir/latin-square-toolbox-$toolbox_version-SNAPSHOT.jar"
tool_output_script_generator="lsg"
tool_output_script_transversal_counter="lstc"
tool_output_script_property_checker="lspc"

# verify that maven is available in the current session
maven_cmd_exists=$(command -v $maven_cmd 2>/dev/null)

# print the fancy header
echo " Building the"
echo "  _           _   _          _____                            " 
echo " | |         | | (_)        / ____|                           " 
echo " | |     __ _| |_ _ _ __   | (___   __ _ _   _  __ _ _ __  ___ "
echo " | |    / _\` | __| | '_ \\   \\___ \\ / _\` | | | |/ _\` | '__\\/ _ \\"
echo " | |___| (_| | |_| | | | |  ____) | (_| | |_| | (_| | |  |  __/"
echo " |______\\__,_|\\__|_|_| |_| |_____/ \\__, |\\__,_|\\__,_|_|   \\___|"
echo "                                      | |                     " 
echo "                                      |_|                     "
echo "	  _______          _ _               "
echo "	 |__   __|        | | |              "
echo "	    | | ___   ___ | | |__   _____  __"
echo "	    | |/ _ \\ / _ \\| | '_ \\ / _ \\ \\/ /"
echo "	    | | (_) | (_) | | |_) | (_) >  < " 
echo "	    |_|\\___/ \\___/|_|_.__/ \\___/_/\\_\\  v1.10"
echo ""

if [ -n $maven_cmd_exists ] ; then

    echo "Found the required Maven command \"$maven_cmd\"! :D "

    # make sure the unit test output directory is created
    unit_test_output_dir="$base_dir/src/test/resources/data/actual";
    if [ ! -d $unit_test_output_dir ]; then
        mkdir $unit_test_output_dir
    fi
    
    # attempt to build the Latin Square Toolbox as deployable jar
    toolbox_jar_build_message=$($maven_cmd package)
    echo "$toolbox_jar_build_message"
    
    # determine if the Latin Square Toolbox jar build was successful
    toolbox_jar_build_message_success=$(echo $toolbox_jar_build_message | grep "BUILD SUCCESS" | wc -l)
    #echo "BUILD GREP MSG: $toolbox_jar_build_message_success"
    
    # verify that the deploy directory and the Latin Square Toolbox jar are available
    if test -d $deploy_dir && [ $toolbox_jar_build_message_success -eq 1 ] && test -f $toolbox_deployed_jar ; then
        # create execution scripts by appending the main classes in the deployable Latin Square Toolbox jar to the Bash script, then grant permissions
        cat $stub_generator $toolbox_deployed_jar > "$base_dir/$tool_output_script_generator" && chmod +x "$base_dir/$tool_output_script_generator"
        cat $stub_transversal_counter $toolbox_deployed_jar > "$base_dir/$tool_output_script_transversal_counter" && chmod +x "$base_dir/$tool_output_script_transversal_counter"
        cat $stub_property_checker $toolbox_deployed_jar > "$base_dir/$tool_output_script_property_checker" && chmod +x "$base_dir/$tool_output_script_property_checker"
         
    else
        echo "There was an error while building the Latin Square Toolbox! Check required dependencies, permissions, configuration, or source code. (⊙ v ☉)"
        exit 1
    fi
    
    # attempt to build the Latin Square Toolbox Javadocs
    toolbox_javadocs_build_message=$($maven_cmd "javadoc:javadoc")
    echo "$toolbox_javadocs_build_message"
    
    # determine if the Latin Square Toolbox Javadocs build was successful
    toolbox_javadocs_build_message_success=$(echo $toolbox_javadocs_build_message | grep "BUILD FAILURE" | wc -l)
    
    if [ $toolbox_javadocs_build_message_success -eq 1 ] ; then
        echo "There was an error while building the Latin Square Toolbox Javadocs!";
    else
         echo "Latin Square Toolbox and its Javadocs were successfully built! :D";
    fi
    
else
    echo "The required Maven command \"$maven_cmd\" is currently not available. (⊙ v ☉)"
fi
