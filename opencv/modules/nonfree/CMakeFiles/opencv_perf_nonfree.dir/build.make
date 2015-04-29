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
include modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/depend.make

# Include the progress variables for this target.
include modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/progress.make

# Include the compile flags for this target's objects.
include modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/flags.make

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/flags.make
modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o: modules/nonfree/perf/perf_gpu.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_1)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_gpu.cpp

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_gpu.cpp > CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.i

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_gpu.cpp -o CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.s

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o.requires:
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o.requires

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o.provides: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o.requires
	$(MAKE) -f modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/build.make modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o.provides.build
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o.provides

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o.provides.build: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/flags.make
modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o: modules/nonfree/perf/perf_main.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_2)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_main.cpp

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_main.cpp > CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.i

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_main.cpp -o CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.s

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o.requires:
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o.requires

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o.provides: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o.requires
	$(MAKE) -f modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/build.make modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o.provides.build
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o.provides

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o.provides.build: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/flags.make
modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o: modules/nonfree/perf/perf_surf.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_3)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_surf.cpp

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_surf.cpp > CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.i

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_surf.cpp -o CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.s

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o.requires:
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o.requires

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o.provides: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o.requires
	$(MAKE) -f modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/build.make modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o.provides.build
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o.provides

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o.provides.build: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/flags.make
modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o: modules/nonfree/perf/perf_surf_ocl.cpp
	$(CMAKE_COMMAND) -E cmake_progress_report /Users/Gideon/Documents/dev/breadboard/opencv/CMakeFiles $(CMAKE_PROGRESS_4)
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Building CXX object modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++   $(CXX_DEFINES) $(CXX_FLAGS) -o CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o -c /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_surf_ocl.cpp

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.i"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -E /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_surf_ocl.cpp > CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.i

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.s"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && /usr/bin/c++  $(CXX_DEFINES) $(CXX_FLAGS) -S /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/perf/perf_surf_ocl.cpp -o CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.s

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o.requires:
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o.requires

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o.provides: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o.requires
	$(MAKE) -f modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/build.make modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o.provides.build
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o.provides

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o.provides.build: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o

# Object files for target opencv_perf_nonfree
opencv_perf_nonfree_OBJECTS = \
"CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o" \
"CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o" \
"CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o" \
"CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o"

# External object files for target opencv_perf_nonfree
opencv_perf_nonfree_EXTERNAL_OBJECTS =

bin/opencv_perf_nonfree: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o
bin/opencv_perf_nonfree: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o
bin/opencv_perf_nonfree: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o
bin/opencv_perf_nonfree: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o
bin/opencv_perf_nonfree: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/build.make
bin/opencv_perf_nonfree: lib/libopencv_core.a
bin/opencv_perf_nonfree: lib/libopencv_flann.a
bin/opencv_perf_nonfree: lib/libopencv_imgproc.a
bin/opencv_perf_nonfree: lib/libopencv_highgui.a
bin/opencv_perf_nonfree: lib/libopencv_features2d.a
bin/opencv_perf_nonfree: lib/libopencv_calib3d.a
bin/opencv_perf_nonfree: lib/libopencv_ml.a
bin/opencv_perf_nonfree: lib/libopencv_video.a
bin/opencv_perf_nonfree: lib/libopencv_legacy.a
bin/opencv_perf_nonfree: lib/libopencv_objdetect.a
bin/opencv_perf_nonfree: lib/libopencv_photo.a
bin/opencv_perf_nonfree: lib/libopencv_gpu.a
bin/opencv_perf_nonfree: lib/libopencv_ocl.a
bin/opencv_perf_nonfree: lib/libopencv_nonfree.a
bin/opencv_perf_nonfree: lib/libopencv_ts.a
bin/opencv_perf_nonfree: lib/libopencv_highgui.a
bin/opencv_perf_nonfree: lib/libopencv_core.a
bin/opencv_perf_nonfree: lib/libopencv_flann.a
bin/opencv_perf_nonfree: lib/libopencv_imgproc.a
bin/opencv_perf_nonfree: lib/libopencv_highgui.a
bin/opencv_perf_nonfree: lib/libopencv_features2d.a
bin/opencv_perf_nonfree: lib/libopencv_video.a
bin/opencv_perf_nonfree: lib/libopencv_gpu.a
bin/opencv_perf_nonfree: lib/libopencv_legacy.a
bin/opencv_perf_nonfree: lib/libopencv_photo.a
bin/opencv_perf_nonfree: lib/libopencv_ocl.a
bin/opencv_perf_nonfree: lib/libopencv_calib3d.a
bin/opencv_perf_nonfree: lib/libopencv_ml.a
bin/opencv_perf_nonfree: lib/libopencv_objdetect.a
bin/opencv_perf_nonfree: lib/libopencv_features2d.a
bin/opencv_perf_nonfree: lib/libopencv_flann.a
bin/opencv_perf_nonfree: lib/libopencv_highgui.a
bin/opencv_perf_nonfree: 3rdparty/lib/liblibjpeg.a
bin/opencv_perf_nonfree: 3rdparty/lib/liblibpng.a
bin/opencv_perf_nonfree: 3rdparty/lib/liblibtiff.a
bin/opencv_perf_nonfree: 3rdparty/lib/liblibjasper.a
bin/opencv_perf_nonfree: 3rdparty/lib/libIlmImf.a
bin/opencv_perf_nonfree: /opt/local/include/../lib/libavcodec.a
bin/opencv_perf_nonfree: /opt/local/include/../lib/libavformat.a
bin/opencv_perf_nonfree: /opt/local/include/../lib/libavutil.a
bin/opencv_perf_nonfree: /opt/local/include/../lib/libswscale.a
bin/opencv_perf_nonfree: /usr/lib/libbz2.dylib
bin/opencv_perf_nonfree: lib/libopencv_video.a
bin/opencv_perf_nonfree: lib/libopencv_imgproc.a
bin/opencv_perf_nonfree: lib/libopencv_core.a
bin/opencv_perf_nonfree: 3rdparty/lib/libzlib.a
bin/opencv_perf_nonfree: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --red --bold "Linking CXX executable ../../bin/opencv_perf_nonfree"
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/opencv_perf_nonfree.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/build: bin/opencv_perf_nonfree
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/build

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/requires: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_gpu.cpp.o.requires
modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/requires: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_main.cpp.o.requires
modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/requires: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf.cpp.o.requires
modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/requires: modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/perf/perf_surf_ocl.cpp.o.requires
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/requires

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/clean:
	cd /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree && $(CMAKE_COMMAND) -P CMakeFiles/opencv_perf_nonfree.dir/cmake_clean.cmake
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/clean

modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/depend:
	cd /Users/Gideon/Documents/dev/breadboard/opencv && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/Gideon/Documents/dev/breadboard/opencv /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree /Users/Gideon/Documents/dev/breadboard/opencv /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree /Users/Gideon/Documents/dev/breadboard/opencv/modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : modules/nonfree/CMakeFiles/opencv_perf_nonfree.dir/depend

