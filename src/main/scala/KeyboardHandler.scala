package io.github.HeavyRain266.helloScala

import org.lwjgl.glfw._
import org.lwjgl.glfw.GLFW._

class KeyboardHandler() extends GLFWKeyCallback {
  override def invoke(
                       window: Long,
                       key: Int,
                       scancode: Int,
                       action: Int,
                       mods: Int
                     ): Unit = {
    if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
      glfwSetWindowShouldClose(window, true )
  }
}