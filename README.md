# seam-carving
LeaderboardSeam-carving is a content-aware image resizing technique where the image is reduced in size by one pixel of height (or width) at a time.

Princeton University CS project.

https://www.cs.princeton.edu/courses/archive/spring22/cos226/assignments/seam/specification.php
https://www.cs.princeton.edu/courses/archive/spring22/cos226/assignments/seam/checklist.php

---

### To run this program:
Clone the repository onto your computer: `$ git clone https://github.com/ShabdanBatyrkulov/seam-carving.git`.

Set up the algs4 library, from the link:
http://algs4.cs.princeton.edu/code/ .
On that page, there are instructions on how to set up the library. 

Once done with that, you will be able to compile java files in your local repo.

For example ResizeDemo.java:
```
/* *****************************************************************************
 *  Compilation:  javac-algs4 ResizeDemo.java
 *  Execution:    java-algs4 ResizeDemo input.png columnsToRemove rowsToRemove
 *  Dependencies: SeamCarver.java
 *                
 *
 *  Read image from file specified as command line argument. Use SeamCarver
 *  to remove number of rows and columns specified as command line arguments.
 *  Display the image and print time elapsed.
 *
 *  % java ResizeDemo HJoceanSmall.png 150 0
 *
 **************************************************************************** */
 ```

Closing the images ends the program.

---

# Output of the program
## Before HJocean.png.      
Removed 150 columns.

![Before](https://github.com/ShabdanBatyrkulov/seam-carving/blob/main/output/Before_HJocean.png)
## After HJocean.png
![After](https://github.com/ShabdanBatyrkulov/seam-carving/blob/main/output/After_HJocean.png)


## Before chameleon.png
Removed 300 columns.

![Before](https://github.com/ShabdanBatyrkulov/seam-carving/blob/main/output/Before_chameleon.png)
## After chameleon.png
![After](https://github.com/ShabdanBatyrkulov/seam-carving/blob/main/output/After_chameleon.png)
