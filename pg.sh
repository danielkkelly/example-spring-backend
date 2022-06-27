#!/usr/local/bin/bash
# Use brew to install postgres.  On newer Macbooks HOMEBREW_HOME is usually
# /opt/homebrew but on older machines brew will install software under 
# /usr/local.  

declare action

function parse_cli {
	for arg in "$@"; do # transform long options to short ones 
		shift
		case "$arg" in
			"--action")       set -- "$@" "-a" ;;
			*)                set -- "$@" "$arg"
		esac
	done

	# Parse command line options safely using getops
	while getopts "a:" opt; do
		case $opt in
			a) action=$OPTARG ;;
			\?)
				echo "Invalid option: -$OPTARG" >&2
				;;
		esac
	done
}

function check_cli { # by making sure that the requied options are supplied, etc.
	declare -a required_opts=("action")

	for opt in ${required_opts[@]};
	do
		if [[ "x${!opt}" == "x" ]]
		then
			echo "$opt is required"
			exit 1;
		fi
	done;
}

# Verify that the prerequisite environment variable exists, otherwise things don't 
# work down the line.
function check_env {
	if [[ "x$HOMEBREW_HOME" == "x" ]] 
	then
		echo "please set HOMEBREW_HOME and try again"
		exit 1
	fi
}

function main {
	case "$action" in 
		start)
			$HOMEBREW_HOME/bin/pg_ctl -D $HOMEBREW_HOME/var/postgres \
						  -l $HOMEBREW_HOME/var/postgres/server.log \
						  start
			;;
		stop)
			$HOMEBREW_HOME/bin/pg_ctl -D $HOMEBREW_HOME/var/postgres stop \
                                              -s -m fast
			;;
		*)
			echo "unknown action $action"
			;;
	esac
}

parse_cli $@
check_env
check_cli
main

