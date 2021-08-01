package io.github.heavyrain266.helloScala

import org.lwjgl.glfw._
import org.lwjgl.glfw.GLFW._

class KeyboardHandler() extends GLFWKeyCallback {
  override def invoke(
      window: Long,
      key: Int,
      scancode: Int,
      action: Int,
      mods: Int
  ): Unit = (key, action) match {
    case (GLFW_KEY_ESCAPE, GLFW_RELEASE) =>
      glfwSetWindowShouldClose(window, true)
    case _ => ()
  }
}
