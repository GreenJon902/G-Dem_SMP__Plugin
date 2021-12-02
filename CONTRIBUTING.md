Welcome, thank you for thinking of contributing, that is greatly appriciated! But first there are a few things you need to do...
1. You need to be in [this discord guild](https://discord.gg/jC4bcx75ef)
2. You also need to apply at [this google form](https://forms.gle/xeiJgGF1xV3MRNbe9)

# How to contribute
In this plugin, we have lots of separate programs called Components, but how does a component work?
### Components
Components are isolated in their own packages inside the main plugin folder com.greenjon902.g_dem__smp, the name of the folder should be the name of the package and inside is a file named after the component. To attach a component to the main plugin, you just need to add a line inside of G_Dem__SMP.components that has a new statement (Please note that this line should end with a comma no matter if it's at the end or not). Components need to implement the PluginComponent interface and should not be static!
### Dependants
If your component requires a dependency then please follow these rules:
1. If it is a minecraft dependent (e.g. Vault) then please make the functionality your self, if you cannot then please contact me.
2. If it is a java dependant then please try to make it yourself, especially if it is something simple, if unsure then please contact me.
### Storage and configuration
At the moment we are using YAML for everything, if you need something else then please contact me.

## How to get support
If you need help or advice then use developing-support channel. If it is specific about the plugin then please contact me on discord at GreenJon#3120!