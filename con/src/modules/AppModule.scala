package modules

import f.service.ConsoleRunnerServiceBeans
import http.service.HttpServiceBeans

trait AppModule extends ConsoleRunnerServiceBeans with HttpServiceBeans

object AppModule:
  def withBeansDo[R](f: AppModule => R): R =
    val appModule = new AppModule {}
    appModule.withBeansDo(f(appModule))
