import os.path

import yaml


def _check_user_folder(user_id):
    if not os.path.exists(os.path.join("./converted", user_id)):
        os.mkdir(os.path.join("./converted", user_id))


def _check_out_folder():
    if not os.path.exists("./converted"):
        os.mkdir("./converted")


def convert(user_id, contents):
    _check_out_folder()
    _check_user_folder(user_id)

    user_data = yaml.load(contents, yaml.Loader)
    user_homes = user_data["homes"]

    for user_home_name in user_homes:
        with open(os.path.join("./converted", user_id, user_home_name) + ".yml", "w") as file:
            yaml.dump(user_homes[user_home_name], file)


if __name__ == "__main__":
    doing_bulk = "."
    while doing_bulk not in "yn":
        doing_bulk = input("Are you doing lots of files [y/n]? ")

    if doing_bulk == "y":
        doing_bulk = True
    else:
        doing_bulk = False

    while True:
        if doing_bulk:
            file_name = input("File name (nothing if your done): ")
            if file_name == "":
                break
        else:
            file_name = input("File name: ")

        print("File contents:")
        lines = []
        while True:
            line = input()
            if line:
                lines.append(line)
            else:
                break
        file_contents = '\n'.join(lines)

        convert(file_name.replace(".yml", ""), file_contents)

        if not doing_bulk:
            break


__all__ = ["convert"]
