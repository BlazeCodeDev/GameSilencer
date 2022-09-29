# GameSilencer

This is a small tool which mutes your media playback while playing certain games. It triggers when you try to run ingame, i.e. Shift + W/A/S/D.

The muting duration can be changed between 1s and 45s

## Currently supported games:
- CounterStrike : Global Offensive
- Rainbow Six Siege

The tool also counts it's own window as "ingame" for testing purposes.

## Bugs / Feature requests
If you encounter any bugs or have a suggestion on how to improve this little tool please open a GitHub issue in this repo. I'll try to answer / help out as soon as possible.

## Privacy
This tool is essentially a key logger. It only listens to W/A/S/D and both your shift keys. When you press one of these combinations it also gets the title name of the currently focused window. It then compares the window name to a set list of names to determine whether it is a game or not.
The tool never sends any information, it doesn't need an active internet connection to be able to work.

## License
MIT License

Copyright (c) 2022 BlazeCodeDev, Ralf Lehmann

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
