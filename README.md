# Compose Tinder Card

![Banner image](/images/banner.png)

A Jetpack Compose `Modifier` that enables Tinder-like card gestures.

## Demo

Click the play button to see the `Modifier.swipeableCard()` in action:

https://user-images.githubusercontent.com/1665273/185766590-363044b6-8ff7-4528-95e2-b04cd70f0ad1.mp4

## How to Install via Gradle

Add the following code in your `app/build.gradle` file:

```gradle
repositories {
...
mavenCentral()
}

dependencies {
    implementation 'com.alexstyl.swipeablecard:swipeablecard:0.0.1'
}
```

## How to use

Add the `Modifier.swipeableCard()` into your `@Composable` function to enable Tinder-like card gestures:

```kotlin
val state = rememberSwipeableCardState()

Box(
    modifier = Modifier
        .fillMaxSize()
        .swipableCard(
            state = state,
            onSwiped = { direction ->
                println("The card was swiped to $direction")
            },
            onSwipeCancel = {
                println("The swiping was cancelled")
            }
        )
) {
  // contents ...
}
```
The `SwipeableCardState` gives you access to the card's _offset_ so that you can create advanced animations according to the amount of swiping done.

The _swipedDirection_ gives you the direction the card has been fully swiped.

## How to swipe programmatically

Use the `SwipeableCardState` to swipe to a specific direction without a gesture:

```kotlin
val state = rememberSwipeableCardState()

// pass the state in your Modifier.swipeableCard(state)

val scope = rememberCoroutineScope()
Button(
    onClick = {
        scope.launch {
            state.swipe(Direction.Right)
        }
    }
) {
    Text("Like")
}
```

The `swipe()` suspend function will return, as soon as the swipe animation is finished.

## How to detect that a card has been swiped away

Use the `SwipeableCardState.swipedDirection`. You may want to combine it with a `LaunchedEffect()` in order to receive a callback when your card is swiped away (using a gesture or programmatically):

```kotlin
LaunchedEffect(state.swipedDirection){
    if(state.swipedDirection!=null) {
        println("The card was swiped to ${state.swipedDirection!!}")
    }
}
```

## Demo app included

Checkout the [app's MainActivity.kt](https://github.com/alexstyl/compose-tinder-card/blob/main/app/src/main/java/com/alexstyl/swipeablecard/MainActivity.kt) to see a fully functioning example of usage.

## How to build the library locally

Include the following snippet in your `local.gradle` file and do a Gradle Sync:
```gradle
sonatypeStagingProfileId=
ossrhUsername=
ossrhPassword=
signing.keyId=
signing.key=
signing.password=
```

The above parameters are used for publishing the library and are not required for development.

## Not accepting non-bug fix contributions

Until the API is in stable state (1.0.0 release), I won't be accepting any contributions other than bug fixes.

If you have an idea, question or feedback, [open a new issue](https://github.com/alexstyl/compose-tinder-card/issues/new).

## License

Apache 2.0. See the [LICENSE](https://github.com/alexstyl/compose-tinder-card/blob/main/LICENSE) file for details.

## Author

Made by Alex Styl. [Follow me on Twitter](https://twitter.com/alexstyl) for updates.

Inspired by [Twypper](https://github.com/theapache64/twyper/) and [react-tinder-card](https://github.com/3DJakob/react-tinder-card)
