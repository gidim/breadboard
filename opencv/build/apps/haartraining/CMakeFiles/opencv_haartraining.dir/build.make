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
CMAKE_COMMAND = /Applications/CMake.app/Contents/bin/cmake

# The command to remove a file.
RM = /Applications/CMake.app/Contents/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/sashabeltinova/Downloads/opencv-2.4.10

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/sashabeltinova/Downloads/opencv-2.4.10/build

# Include any dependencies generated for this target.
include apps/haartraining/CMakeFiles/opencv_haartraining.dir/depend.make

# Include the progress variables for this target.
include apps/haartraining/CMakeFiles/opencv_haartraining.dir/progress.make

# Include the compile flags for this target's objects.
include apps/haartraining/CMakeFiles/opencv_haartraining.dir/flags.make

apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o: apps/haartraining/CMakeFiles/opencv_haartraining.dir/flags.make
apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o: ../apps/haartraining/haartraining.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/sashabeltinova/Downloads/opencv-2.4.10/build/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o"
	cd /Users/sashabeltinova/Downloads/opencv-2.4.10/build/apps/haartraining && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o -c /Users/sashabeltinova/Downloads/opencv-2.4.10/apps/haartraining/haartraining.cpp

apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_haartraining.dir/haartraining.cpp.i"
	cd /Users/sashabeltinova/Downloads/opencv-2.4.10/build/apps/haartraining && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/sashabeltinova/Downloads/opencv-2.4.10/apps/haartraining/haartraining.cpp > CMakeFiles/opencv_haartraining.dir/haartraining.cpp.i

apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_haartraining.dir/haartraining.cpp.s"
	cd /Users/sashabeltinova/Downloads/opencv-2.4.10/build/apps/haartraining && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/sashabeltinova/Downloads/opencv-2.4.10/apps/haartraining/haartraining.cpp -o CMakeFiles/opencv_haartraining.dir/haartraining.cpp.s

apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o.requires:
.PHONY : apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o.requires

apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o.provides: apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o.requires
	$(MAKE) -f apps/haartraining/CMakeFiles/opencv_haartraining.dir/build.make apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o.provides.build
.PHONY : apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o.provides

apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o.provides.build: apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o

# Object files for target opencv_haartraining
opencv_haartraining_OBJECTS = \
"CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o"

# External object files for target opencv_haartraining
opencv_haartraining_EXTERNAL_OBJECTS =

bin/opencv_haartraining: apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o
bin/opencv_haartraining: apps/haartraining/CMakeFiles/opencv_haartraining.dir/build.make
bin/opencv_haartraining: lib/libopencv_core.a
bin/opencv_haartraining: lib/libopencv_imgproc.a
bin/opencv_haartraining: lib/libopencv_highgui.a
bin/opencv_haartraining: lib/libopencv_objdetect.a
bin/opencv_haartraining: lib/libopencv_calib3d.a
bin/opencv_haartraining: lib/libopencv_video.a
bin/opencv_haartraining: lib/libopencv_features2d.a
bin/opencv_haartraining: lib/libopencv_flann.a
bin/opencv_haartraining: lib/libopencv_legacy.a
bin/opencv_haartraining: lib/libopencv_haartraining_engine.a
bin/opencv_haartraining: lib/libopencv_objdetect.a
bin/opencv_haartraining: lib/libopencv_legacy.a
bin/opencv_haartraining: lib/libopencv_calib3d.a
bin/opencv_haartraining: lib/libopencv_video.a
bin/opencv_haartraining: lib/libopencv_features2d.a
bin/opencv_haartraining: lib/libopencv_highgui.a
bin/opencv_haartraining: lib/libopencv_imgproc.a
bin/opencv_haartraining: 3rdparty/lib/liblibjpeg.a
bin/opencv_haartraining: 3rdparty/lib/liblibpng.a
bin/opencv_haartraining: 3rdparty/lib/liblibtiff.a
bin/opencv_haartraining: 3rdparty/lib/liblibjasper.a
bin/opencv_haartraining: 3rdparty/lib/libIlmImf.a
bin/opencv_haartraining: lib/libopencv_flann.a
bin/opencv_haartraining: lib/libopencv_ml.a
bin/opencv_haartraining: lib/libopencv_core.a
bin/opencv_haartraining: 3rdparty/lib/libzlib.a
bin/opencv_haartraining: apps/haartraining/CMakeFiles/opencv_haartraining.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable ../../bin/opencv_haartraining"
	cd /Users/sashabeltinova/Downloads/opencv-2.4.10/build/apps/haartraining && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/opencv_haartraining.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
apps/haartraining/CMakeFiles/opencv_haartraining.dir/build: bin/opencv_haartraining
.PHONY : apps/haartraining/CMakeFiles/opencv_haartraining.dir/build

apps/haartraining/CMakeFiles/opencv_haartraining.dir/requires: apps/haartraining/CMakeFiles/opencv_haartraining.dir/haartraining.cpp.o.requires
.PHONY : apps/haartraining/CMakeFiles/opencv_haartraining.dir/requires

apps/haartraining/CMakeFiles/opencv_haartraining.dir/clean:
	cd /Users/sashabeltinova/Downloads/opencv-2.4.10/build/apps/haartraining && $(CMAKE_COMMAND) -P CMakeFiles/opencv_haartraining.dir/cmake_clean.cmake
.PHONY : apps/haartraining/CMakeFiles/opencv_haartraining.dir/clean

apps/haartraining/CMakeFiles/opencv_haartraining.dir/depend:
	cd /Users/sashabeltinova/Downloads/opencv-2.4.10/build && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/sashabeltinova/Downloads/opencv-2.4.10 /Users/sashabeltinova/Downloads/opencv-2.4.10/apps/haartraining /Users/sashabeltinova/Downloads/opencv-2.4.10/build /Users/sashabeltinova/Downloads/opencv-2.4.10/build/apps/haartraining /Users/sashabeltinova/Downloads/opencv-2.4.10/build/apps/haartraining/CMakeFiles/opencv_haartraining.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : apps/haartraining/CMakeFiles/opencv_haartraining.dir/depend

