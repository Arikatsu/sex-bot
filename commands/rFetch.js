const { getImage } = require('random-reddit')

module.exports = {
    callback: async (message, ...args) => {
        let subreddit = args[0]
        const image = await getImage(subreddit, 20)
        message.channel.send(image)
    }
}