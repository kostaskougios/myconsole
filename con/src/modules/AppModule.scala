package modules

import f.service.ConsoleRunnerServiceBeans
import http.service.HttpServiceBeans

trait AppModule extends ConsoleRunnerServiceBeans with HttpServiceBeans

object StaticAppModule extends AppModule
