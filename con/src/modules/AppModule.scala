package modules

import http.service.HttpServiceBeans

trait AppModule extends HttpServiceBeans

object AppModule:
  def withBeansDo[R](f: AppModule => R): R =
    val appModule = new AppModule {}
    appModule.withBeansDo(f(appModule))
