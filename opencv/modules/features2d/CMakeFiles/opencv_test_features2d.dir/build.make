# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.1

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list

# Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /usr/local/Cellar/cmake/3.1.2/bin/cmake

# The command to remove a file.
RM = /usr/local/Cellar/cmake/3.1.2/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/Gideon/Documents/dev/breadboard/opencv

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/Gideon/Documents/dev/breadboard/opencv

# Include any dependencies generated for this target.
include modules/features2d/CMakeFiles/opencv_test_features2d.dir/depend.make

# Include the progress variables for this target.
include modules/features2d/CMakeFiles/opencv_test_features2d.dir/progress.make

# Include the compile flags for this target's objects.
include modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o: modules/features2d/test/test_brisk.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_brisk.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_brisk.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_brisk.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o: modules/features2d/test/test_descriptors_regression.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_2)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_descriptors_regression.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_descriptors_regression.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_descriptors_regression.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o: modules/features2d/test/test_detectors_regression.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_3)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_detectors_regression.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_detectors_regression.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_detectors_regression.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o: modules/features2d/test/test_fast.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_4)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_fast.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_fast.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_fast.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o: modules/features2d/test/test_keypoints.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_5)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_keypoints.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_keypoints.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_keypoints.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o: modules/features2d/test/test_main.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_6)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_main.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_main.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_main.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o: modules/features2d/test/test_matchers_algorithmic.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_7)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_matchers_algorithmic.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_matchers_algorithmic.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_matchers_algorithmic.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o: modules/features2d/test/test_mser.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_8)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_mser.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_mser.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_mser.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o: modules/features2d/test/test_nearestneighbors.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_9)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_nearestneighbors.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_nearestneighbors.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_nearestneighbors.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o: modules/features2d/test/test_orb.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_10)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_orb.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_orb.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_orb.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o: modules/features2d/CMakeFiles/opencv_test_features2d.dir/flags.make
modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o: modules/features2d/test/test_rotation_and_scale_invariance.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_11)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_rotation_and_scale_invariance.cpp

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_rotation_and_scale_invariance.cpp > CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.i

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/test/test_rotation_and_scale_invariance.cpp -o CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.s

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o.requires:
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o.requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o.provides: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o.requires
	$(MAKE) -f modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o.provides.build
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o.provides

modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o.provides.build: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o

# Object files for target opencv_test_features2d
opencv_test_features2d_OBJECTS = \
"CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o" \
"CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o"

# External object files for target opencv_test_features2d
opencv_test_features2d_EXTERNAL_OBJECTS =

bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/build.make
bin/opencv_test_features2d: lib/libopencv_core.a
bin/opencv_test_features2d: lib/libopencv_flann.a
bin/opencv_test_features2d: lib/libopencv_imgproc.a
bin/opencv_test_features2d: lib/libopencv_highgui.a
bin/opencv_test_features2d: lib/libopencv_features2d.a
bin/opencv_test_features2d: lib/libopencv_ts.a
bin/opencv_test_features2d: lib/libopencv_highgui.a
bin/opencv_test_features2d: lib/libopencv_core.a
bin/opencv_test_features2d: lib/libopencv_flann.a
bin/opencv_test_features2d: lib/libopencv_imgproc.a
bin/opencv_test_features2d: lib/libopencv_highgui.a
bin/opencv_test_features2d: lib/libopencv_features2d.a
bin/opencv_test_features2d: lib/libopencv_video.a
bin/opencv_test_features2d: lib/libopencv_flann.a
bin/opencv_test_features2d: lib/libopencv_highgui.a
bin/opencv_test_features2d: 3rdparty/lib/liblibjpeg.a
bin/opencv_test_features2d: 3rdparty/lib/liblibpng.a
bin/opencv_test_features2d: 3rdparty/lib/liblibtiff.a
bin/opencv_test_features2d: 3rdparty/lib/liblibjasper.a
bin/opencv_test_features2d: 3rdparty/lib/libIlmImf.a
bin/opencv_test_features2d: /opt/local/include/../lib/libavcodec.a
bin/opencv_test_features2d: /opt/local/include/../lib/libavformat.a
bin/opencv_test_features2d: /opt/local/include/../lib/libavutil.a
bin/opencv_test_features2d: /opt/local/include/../lib/libswscale.a
bin/opencv_test_features2d: /usr/lib/libbz2.dylib
bin/opencv_test_features2d: lib/libopencv_imgproc.a
bin/opencv_test_features2d: lib/libopencv_core.a
bin/opencv_test_features2d: 3rdparty/lib/libzlib.a
bin/opencv_test_features2d: modules/features2d/CMakeFiles/opencv_test_features2d.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable ../../bin/opencv_test_features2d"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/opencv_test_features2d.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
modules/features2d/CMakeFiles/opencv_test_features2d.dir/build: bin/opencv_test_features2d
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/build

modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_brisk.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_descriptors_regression.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_detectors_regression.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_fast.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_keypoints.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_main.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_matchers_algorithmic.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_mser.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_nearestneighbors.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_orb.cpp.o.requires
modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires: modules/features2d/CMakeFiles/opencv_test_features2d.dir/test/test_rotation_and_scale_invariance.cpp.o.requires
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/requires

modules/features2d/CMakeFiles/opencv_test_features2d.dir/clean:
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d && $(CMAKE_COMMAND) -P CMakeFiles/opencv_test_features2d.dir/cmake_clean.cmake
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/clean

modules/features2d/CMakeFiles/opencv_test_features2d.dir/depend:
	cd /Users/Gideon/Documents/dev/breadboard/opencv && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/Gideon/Documents/dev/breadboard/opencv /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d /Users/Gideon/Documents/dev/breadboard/opencv /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d /Users/Gideon/Documents/dev/breadboard/opencv/modules/features2d/CMakeFiles/opencv_test_features2d.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : modules/features2d/CMakeFiles/opencv_test_features2d.dir/depend

