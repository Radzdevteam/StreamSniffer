# StreamSniffer

```markdown

StreamSniffer is an Android library designed to extract `.m3u8` streaming links from embedded webpage content and seamlessly play them using ExoPlayer. It also supports detecting subtitle files (`.srt` and `.vtt`) for an enhanced media playback experience.

---

## Features
- **Stream Extraction**: Extract `.m3u8` URLs from webpages.
- **Subtitle Support**: Detect and log `.srt` and `.vtt` subtitle files.
- **ExoPlayer Integration**: Send extracted `.m3u8` URLs to ExoPlayer for smooth streaming.
- **WebView-based Sniffing**: Uses a `WebView` to parse and detect embedded links dynamically.

---
```
## How to Include

### Step 1: Add the repository to your project `settings.gradle`:
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

### Step 2: Add the dependency in `build.gradle`:
```kotlin
dependencies {
    implementation("com.github.Radzdevteam:StreamSniffer:Tag")
}
```

Replace `Tag` with the specific version or branch name you want to include.

---

## Example Usage

### `MainActivity`
The following example demonstrates how to launch the `m3u8` activity with a hardcoded URL:

```kotlin
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.radzdev.sniff.m3u8

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hardcoded URL
        val url = "https://vidlink.pro/movie/786892"

        // Start the m3u8 activity with the hardcoded URL
        val intent = Intent(this, m3u8::class.java).apply {
            putExtra("url", url)
        }
        startActivity(intent)

        // Finish the MainActivity immediately
        finish()
    }
}
```

---

## License
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT). Feel free to use and modify it according to your needs.

---

## Contributing
We welcome contributions! Feel free to open issues, suggest features, or submit pull requests to enhance the project.

---

Happy streaming with **StreamSniffer**!
```
