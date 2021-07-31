package io.github.heavyrain266.helloScala

import org.lwjgl.glfw._
import org.lwjgl.glfw.GLFW._
import org.lwjgl.opengl._
import org.lwjgl.opengl.GL11._
import org.lwjgl.system.MemoryUtil._

class HelloScala {
  private object Window {
    var instance: Long = 0
    val title: String = "Hello, Scala!"
    val width: Int = 800
    val height: Int = 600
  }

  def start(): Unit = {
    try {
      init()
      loop()

      glfwDestroyWindow(Window.instance)
    } finally {
      glfwTerminate()
    }
  }

  private def init(): Unit = {
    if (!glfwInit()) {
      throw new IllegalThreadStateException("Init: Failed to initialize GLFW!")
    }

    glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))
    glfwSetKeyCallback(Window.instance, new KeyboardHandler())

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    Window.instance = glfwCreateWindow(Window.width, Window.height, Window.title, NULL, NULL)
    if (Window.instance == NULL) {
      throw new RuntimeException("Init: Failed to create new GLFW window instance!")
    }

    val videoMode: GLFWVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

    if (videoMode != null) {
      glfwSetWindowPos(
        Window.instance,
        (videoMode.width() - Window.width) / 2,
        (videoMode.height() - Window.height) / 2
      )
    }
    glfwMakeContextCurrent(Window.instance)
    glfwSwapInterval(1)
    glfwShowWindow(Window.instance)
  }

  private def loop(): Unit = {
    GL.createCapabilities()

    glClearColor(1.0f, 0.0f, 0.0f, 0.0f)

    while (!glfwWindowShouldClose(Window.instance)) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
      glfwSwapBuffers(Window.instance)
      glfwPollEvents()
    }
  }
}
