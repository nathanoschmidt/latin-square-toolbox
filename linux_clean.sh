#!/bin/sh

# set clean parameters
base_dir="."
unit_test_output_dir="$base_dir/src/test/resources/data/actual";
maven_cmd="mvn"
tool_script_generator="$base_dir/lsg"
tool_script_transversal_counter="$base_dir/lstc"
tool_script_property_checker="$base_dir/lspc"

# execute clean commands
$maven_cmd clean
rm -f $tool_script_generator
rm -f $tool_script_transversal_counter
rm -f $tool_script_property_checker
rm -f $unit_test_output_dir/*.txt

