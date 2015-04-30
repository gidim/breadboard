1. Install HomeBrew  - The missing package manager for OS X
Paste this in terminal and follow the instructions
$ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

 2. Install OpenCV:
$ brew install homebrew/science/opencv --with-java

3. Add the jar file to your classpath
Instruction for IntelliJ:
File -> Project Structure - > Libraries -> Add New (Little + Icon)
Choose the jar file in this path:
/usr/local/Cellar/opencv/2.4.10.1/share/OpenCV/java/opencv-2410.jar


You should now have OpenCV installed and working, you can import org.opencv.* and before doing anything else make sure you load OpenCv by adding this line:

System.load(new File("/usr/local/Cellar/opencv/2.4.10.1/share/OpenCV/java/libopencv_java2410.dylib").getAbsolutePath());