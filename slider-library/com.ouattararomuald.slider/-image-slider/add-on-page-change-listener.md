---
title: ImageSlider.addOnPageChangeListener - slider-library
---

[slider-library](../../index.html) / [com.ouattararomuald.slider](../index.html) / [ImageSlider](index.html) / [addOnPageChangeListener](./add-on-page-change-listener.html)

# addOnPageChangeListener

`fun ~~addOnPageChangeListener~~(pageChangeListener: OnPageChangeListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)
**Deprecated:** Use setOnPageChangeListener instead

Add a listener that will be invoked whenever the page changes or is incrementally scrolled.
See [ViewPager.OnPageChangeListener](#).

Components that add a listener should take care to remove it when finished. Other components
that take ownership of a view may call [clearOnPageChangeListeners](clear-on-page-change-listeners.html) to remove all
attached listeners.

### Parameters

`pageChangeListener` - Listener to add.