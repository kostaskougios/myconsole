package modules

import f.service.RunnerServiceBeans
import http.service.HttpServiceBeans

trait AppModule extends RunnerServiceBeans with HttpServiceBeans

object StaticAppModule extends AppModule
