# ContextualCards
[![Figma Designs](https://img.shields.io/badge/Api-Json-green)](https://run.mocky.io/v3/04a04703-5557-4c84-a127-8c55335bb3b4) &nbsp; &nbsp;
[![Figma Designs](https://img.shields.io/badge/Design-Figma-important)](https://www.figma.com/file/WAKTJJB0vOqU07UMJDcYg0/AAL3-%3A-Android-assignment-Design-Specs?node-id=4287%3A35) <br><br>
A standalone Plug-and-Play container that can be used to display Contextual Cards that are rendered using JSON from an API

## Screenshots
<p>
    <img src="https://github.com/akri16/ContextualCards/blob/master/assets/ss-1.jpg" width="200" />
    <img src="https://github.com/akri16/ContextualCards/blob/master/assets/ss-2.jpg" width="200"/>
    <img src="https://github.com/akri16/ContextualCards/blob/master/assets/ss-3.jpg" width="200"/>
</p>

## Architecture

It uses **Model View ViewModel (MVVM)** architecture <br> <br>
<p align="center"><img alt="Architecture Diagram" src="https://github.com/akri16/ContextualCards/blob/master/assets/arch-diagram.png" width="500"/></p>

## Usage

Plug this Component into your XML and play with it in your Activity/Fragment 😄

```xml
<com.akribase.cardcomponent.ui.CardComponent
            android:id="@+id/component"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
```

```kt
private fun initComponent(component: CardComponent) {
        component.onFetch = { viewModel.fetchUISpec() }
        component.onHC3Remove = { viewModel.remove(it) }
        viewModel.isFetching.observe(this) {it:Boolean -> component.isLoading = it }
        viewModel.uiSpec.observe(this) {it:List<CardGroup> -> component.render(it) }
}

```

## Todo

- [x]  Init the app
- [x]  Design different Cards
    - [x]  HC3
    - [x]  HC6
    - [x]  HC5
    - [x]  HC1
    - [x]  HC9
- [x]  Build models
- [x]  Build Simple Renderables
- [x]  Link the Renderables to the design
- [x]  Build Recycler Views for Card Groups
- [x]  Convert models to renderables
- [x]  Add Styling
- [x]  Connect to API
- [x]  Bug-Fixes
- [x]  Create Repository and Push to Github
- [x]  Create README
- [x]  Add Swipe to Refresh
- [x]  Add HC3 long click effect
    - [x]  Implement Animation
    - [x]  Make Rv use ListAdapter with DiffUtil
    - [x]  Dismiss on click dismiss
    - [x]  Save on click remind later
- [x]  Make the Container Stand-alone

## **Building**

It is recommended that you run Gradle with the `--daemon` option, as starting up the tool from scratch often takes at least a few seconds. You can kill the java process that it leaves running once you are done running your commands.

Tasks work much like Make targets, so you may concatenate them. Tasks are not re-done if multiple targets in a single command require them. For example, running `assemble install` will not compile the apk twice even though `install` depends on `assemble`.

### **Clean**

`gradle clean`

### **Debug**

This compiles a debugging apk in `build/outputs/apk/` signed with a debug key, ready to be installed for testing purposes.

`gradle assembleDebug`

You can also install it on your attached device:

`gradle installDebug`

### **Release**

This compiles an unsigned release (non-debugging) apk in `build/outputs/apk/`. It's not signed, you must sign it before it can be installed by any users.

`gradle assembleRelease`

### **Test**

Were you to add automated java tests, you could configure them in your `build.gradle` file and run them within gradle as well.

`gradle test`

### **Lint**

This analyses the code and produces reports containing warnings about your application in `build/outputs/lint/`.

`gradle lint`

## **Further reading**

- [Build System Overview](https://developer.android.com/sdk/installing/studio-build.html)
- [Gradle Plugin User Guide](http://tools.android.com/tech-docs/new-build-system/user-guide)
- [Gradle Plugin Release Notes](http://tools.android.com/tech-docs/new-build-system)


## License

    MIT License

    Copyright (c) 2021 Amit Krishna A

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
