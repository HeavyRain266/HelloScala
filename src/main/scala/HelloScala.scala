package io.github.HeavyRain266.helloScala

import org.lwjgl._
import org.lwjgl.glfw._
import org.lwjgl.opengl._
import org.lwjgl.glfw.GLFW._
import org.lwjgl.opengl.GL11._
import org.lwjgl.system.MemoryUtil._

class HelloScala {
  private var window: Long = 0

  def start(): Unit = {
    System.out.println("Hello LWJGL " + Version.getVersion + "!")

    try {
      init()
      loop()

      glfwDestroyWindow(window)
    } finally {
      glfwTerminate()
    }
  }

  private def init(): Unit = {
    glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err))

    if (!glfwInit())
      throw new IllegalThreadStateException("Unable to initialize GLFW")

    glfwDefaultWindowHints()
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE)
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE)

    val WIDTH: Int = 800
    val HEIGHT: Int = 600

    window = glfwCreateWindow(WIDTH, HEIGHT, "Hello World!", NULL, NULL)
    if (window == NULL)
      throw new RuntimeException("Failed to create the GLFW window!")

    val kb = new KeyboardHandler()
    glfwSetKeyCallback(window, kb)

    val videoMode: GLFWVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())

    if (videoMode != null) {
      glfwSetWindowPos(
        window,
        (videoMode.width() - WIDTH) / 2,
        (videoMode.height() - HEIGHT) / 2
      )
    }
    glfwMakeContextCurrent(window)
    glfwSwapInterval(1)
    glfwShowWindow(window)
  }

  private def loop(): Unit = {
    GL.createCapabilities()

    glClearColor(1.0f, 0.0f, 0.0f, 0.0f)

    while (!glfwWindowShouldClose(window)) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT) // Clear framebuffer.
      glfwSwapBuffers(window)
      glfwPollEvents()
    }
  }
}