# AnimatedBands
Java 14 (OpenJDK) 
JavaFX 14 (OpenJFX)

Demonstrates the generation of animated bands based on points of randomized length (gaussian) along an arc around a centroid (mouse click). Points of band are animated
using a Timeline to move away from the centroid. Band is rendered using a Path which generates QuadCurves using the points as control points for a smooth fit. This is inspired by the old JavaFX 1.x Ripple generator which only animated simple circles.
