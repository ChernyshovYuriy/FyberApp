FyberApp
========

This is a concept mobile application for Android which utilizes Fyber API.
Documentation available at this link http://developer.fyber.com/content/android/offer-wall/offer-api/

Main concepts which are in use inside this projects are:
- Separate UI and everything that belongs to it (all views and theirs modifications, interactions and listeners, etc ..)
  from the business logic.
- Define main key entities as Interfaces or Abstractions in order to operate with theirs implementations
  which gives a power work with different strategies, for example provide "fake" data (true data) for the methods
  in order to test functionality.
- There is no full implementation of all parameters both in request and response entities, the main ones are
  implemented in order just to show correct functionalities of the application.
- Application keep result of the last request when rotating device in the Bundle in order to restore it's state
  when recreated.
- Android Tests cover main aspects of the application: instance creation, methods calls, bound values check
  for the parameters, parsing correctness, mock of the interfaces. In real project the number of tests can be
  thousands (any kind: Unit, Integrational, etc ...).
