# State Machine
A state machine write in Java using states, transitions and conditions. This code was inspired by the following answer in Stack OverFlow: http://stackoverflow.com/a/2064170/2387977.

# Build

Just:

```shell
./gradlew build
```

# Run

The repository have two tests:

 - The **StateMachineTest** has the example from Stack OverFlow.

 - The **StateMachineAccountTest** is a state machine using the same mechanism, but using an enterprise example for an Account state machine. 

The example below is a user account state diagram represented by the code in StateMachineAccountTest class. This diagram can be [visualized here too](http://www.uml-diagrams.org/examples/online-shopping-user-account-state-diagram-example.png).



```
                      +----------+
                      |          |
+---------------------+   new    |  [isAccountDourmant()] suspend/
|                     |          +-----------------------------------------+
|                     +---+------+                                         |
|                         |                                                |
|           [isVerified() |                                                |
|           isUniqueId()] |                                                |
|               activate/ |                                                |
|                         |                                                |
|                     +---v------+ [isSuspendedRequested()] suspend/ +-----v-----+
|                     |          +----------------------------------->           |
|                     |          | [isPasswordAlert()] lock/         |           |
|                     |          +----------------------------------->           |
|[isCancelRequested()]|          | [isAccountDormant()] suspend/     |           |
|cancel/              |  Active  +-----------------------------------> Suspended |
|                     |          |                                   |           |
|                     |          |      [isResumeRequested()] resume/|           |
|                     |          <-----------------------------------+           |
|                     |          |          [isLockExpired()] unlock/|           |
|                     |          <-----------------------------------+           |
|                     +-+------+-+                                   +--+------+-+
|                       |      |                                        |      |
| [isCancelRequested()] |      |  [isPolicyViolated()]                  |      |
|               cancel/ |      |  cancel/                               |      |
|                       |      |                                        |      |
|                       |      |                                        |      |
|                       |      |                                        |      |
|                       |      |                                        |      |
|                     +-v------v-+                                      |      |
|                     |          |                                      |      |
|                     |          |  [isCancelRequested()] cancel/       |      |
|                     |          <--------------------------------------+      |
|                     |  Closed  |                                             |
+--------------------->          |                                             |
                      |          |   [isPolicyViolated()] cancel/              |
                      |          <---------------------------------------------+
                      |          |
                      +----------+


```
